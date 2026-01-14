package com.davidnascimento.plugins.stepcounter;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;

import com.getcapacitor.*;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.getcapacitor.annotation.Permission;
import com.getcapacitor.annotation.PermissionCallback;


@CapacitorPlugin(
  name = "StepCounter",
  permissions = {
    @Permission(
      alias = "activityRecognition",
      strings = { android.Manifest.permission.ACTIVITY_RECOGNITION }
    )
  }
)
public class StepCounterPlugin extends Plugin implements SensorEventListener {

  private SensorManager sensorManager;
  private Sensor stepSensor;
  private float currentSteps = 0;

  @Override
  public void load() {
    sensorManager = (SensorManager)
      getContext().getSystemService(Context.SENSOR_SERVICE);
    stepSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
  }

  @PermissionCallback
  private void permissionCallback(PluginCall call) {

    if (getPermissionState("activityRecognition") == PermissionState.GRANTED) {
      startSensor(call);
    } else {
      call.reject("ACTIVITY_RECOGNITION permission denied");
    }
  }

  @PluginMethod
  public void start(PluginCall call) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q &&
      !getPermissionState("activityRecognition").equals(PermissionState.GRANTED)) {
      requestPermissionForAlias("activityRecognition", call, "permissionCallback");
      return;
    }

    if (stepSensor == null) {
      call.reject("Step Counter sensor not available");
      return;
    }

    sensorManager.registerListener(
      this,
      stepSensor,
      SensorManager.SENSOR_DELAY_NORMAL
    );

    call.resolve();
  }

  private void startSensor(PluginCall call) {

    if (stepSensor == null) {
      call.reject("Step Counter sensor not available");
      return;
    }

    sensorManager.registerListener(
      this,
      stepSensor,
      SensorManager.SENSOR_DELAY_NORMAL
    );

    call.resolve();
  }


  @PluginMethod
  public void stop(PluginCall call) {
    sensorManager.unregisterListener(this);
    call.resolve();
  }

  @PluginMethod
  public void getCurrentSteps(PluginCall call) {
    JSObject ret = new JSObject();
    ret.put("steps", currentSteps);
    call.resolve(ret);
  }

  @Override
  public void onSensorChanged(SensorEvent event) {
    currentSteps = event.values[0];

    JSObject data = new JSObject();
    data.put("steps", currentSteps);

    notifyListeners("step", data);
  }

  @Override
  public void onAccuracyChanged(Sensor sensor, int accuracy) {}
}
