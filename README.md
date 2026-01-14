# step-counter

A Capacitor plugin that provides access to the **Android Step Counter sensor**
(`TYPE_STEP_COUNTER`), allowing apps to read step count data and receive real-time
step updates. Includes a web fallback for non-Android platforms.

---

## ⚠️ Platform Support

| Platform | Supported |
|---------|-----------|
| Android | ✅ Yes |
| Web     | ⚠️ Fallback (no real sensor) |
| iOS     | ❌ Not supported |

> This plugin relies on Android’s `TYPE_STEP_COUNTER` sensor, which is **not available on iOS**.

---

## 📦 Installation

```bash
npm install step-counter
npx cap sync


🔐 Android Permissions

On Android 10 (API 29) and above, this plugin requires the
ACTIVITY_RECOGNITION permission.

The permission is:

Automatically requested when calling start()

Mandatory for accessing step counter data

🧠 How the Step Counter Works

Uses Sensor.TYPE_STEP_COUNTER

The step count represents total steps since the device last rebooted

The value cannot be reset programmatically

For daily or session-based steps, store a baseline value in your app

🚀 API
start()
start() => Promise<void>


Starts listening to the Android step counter sensor.

Requests ACTIVITY_RECOGNITION permission if required

Throws an error if the sensor is not available on the device

stop()
stop() => Promise<void>


Stops listening to the step counter sensor.

getCurrentSteps()
getCurrentSteps() => Promise<StepCounterData>


Returns the current number of steps since the device last boot.

addListener('step', ...)
addListener(
  eventName: 'step',
  listenerFunc: (data: StepCounterData) => void
) => Promise<PluginListenerHandle>


Emits step updates whenever the system sensor reports a change.

Emits the total accumulated steps since boot

Triggered in real time

🧩 Interfaces
StepCounterData
Prop	Type	Description
steps	number	Total number of steps since device boot
PluginListenerHandle
Prop	Type
remove	() => Promise<void>
📝 Example Usage
import { StepCounter } from 'step-counter';

await StepCounter.start();

const listener = await StepCounter.addListener('step', data => {
  console.log('Steps:', data.steps);
});

// Stop listening
await StepCounter.stop();
await listener.remove();

❗ Notes & Limitations

Step count resets when the device restarts

Not all Android devices include a step counter sensor

This plugin does not manage background execution

Background behavior depends on OS and app lifecycle