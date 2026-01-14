# 🚶‍♂️ Capacitor Step Counter

A **Capacitor plugin** that provides access to the **Android Step Counter sensor** (`Sensor.TYPE_STEP_COUNTER`).
It allows your app to **read step count data** and **receive real-time step updates**, with a **web fallback** for non-Android platforms.

---

## ⚠️ Platform Support

| Platform    | Status          | Notes                       |
| ----------- | --------------- | --------------------------- |
| **Android** | ✅ Supported     | Uses `TYPE_STEP_COUNTER`    |
| **Web**     | ⚠️ Fallback     | Mock / simulated behavior   |
| **iOS**     | ❌ Not supported | Sensor not available on iOS |

> ℹ️ This plugin relies on Android’s **`TYPE_STEP_COUNTER` sensor**, which is **not available on iOS devices**.

---

## 📦 Installation

```bash
npm install @dreiver1/capacitor-step-counter
npx cap sync
```

---

## 🔐 Android Permissions

On **Android 10 (API 29)** and above, this plugin requires the permission:

```xml
android.permission.ACTIVITY_RECOGNITION
```

### Permission behavior

* ✅ Automatically requested when calling `start()`
* 🔒 Mandatory to access step counter data

---

## 🧠 How the Step Counter Works

* Uses **`Sensor.TYPE_STEP_COUNTER`**
* The step count represents the **total number of steps since the last device reboot**
* ⚠️ The value **cannot be reset programmatically**

### 💡 Recommended approach

To track **daily**, **weekly**, or **session-based** steps:

1. Store a **baseline value** when tracking starts
2. Subtract it from the current sensor value

---

## 🚀 API Reference

### ▶️ `start()`

```ts
start() => Promise<void>
```

Starts listening to the Android step counter sensor.

* Requests `ACTIVITY_RECOGNITION` permission if required
* Throws an error if the sensor is not available

---

### ⏹ `stop()`

```ts
stop() => Promise<void>
```

Stops listening to the step counter sensor.

---

### 🔢 `getCurrentSteps()`

```ts
getCurrentSteps() => Promise<StepCounterData>
```

Returns the current number of steps since the device last booted.

---

### 📡 `addListener('step', ...)`

```ts
addListener(
  eventName: 'step',
  listenerFunc: (data: StepCounterData) => void
) => Promise<PluginListenerHandle>
```

Emits step updates whenever the system sensor reports a change.

* Emits the **total accumulated steps since boot**
* Triggered **in real time**

---

## 🧩 Interfaces

### `StepCounterData`

| Property | Type     | Description                             |
| -------- | -------- | --------------------------------------- |
| `steps`  | `number` | Total number of steps since device boot |

---

### `PluginListenerHandle`

| Property | Type                  | Description                |
| -------- | --------------------- | -------------------------- |
| `remove` | `() => Promise<void>` | Removes the event listener |

---

## 📝 Example Usage

```ts
import { StepCounter } from '@dreiver1/capacitor-step-counter';

await StepCounter.start();

const listener = await StepCounter.addListener('step', data => {
  console.log('Steps:', data.steps);
});

// Stop listening
await StepCounter.stop();
await listener.remove();
```

---

## ❗ Notes & Limitations

* 🔄 Step count resets when the device restarts
* 📱 Not all Android devices include a step counter sensor
* 💤 This plugin does **not manage background execution**
* ⚙️ Background behavior depends on OS version and app lifecycle

---

## 📄 License

MIT © David Nascimento
