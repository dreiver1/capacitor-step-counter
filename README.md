# step-counter

A Capacitor plugin that provides access to the device step counter sensor on Androi, with a web fallback.

## Install

```bash
npm install step-counter
npx cap sync
```

## API

<docgen-index>

* [`start()`](#start)
* [`stop()`](#stop)
* [`getCurrentSteps()`](#getcurrentsteps)
* [`addListener('step', ...)`](#addlistenerstep-)
* [Interfaces](#interfaces)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### start()

```typescript
start() => Promise<void>
```

Starts listening to the step counter sensor

--------------------


### stop()

```typescript
stop() => Promise<void>
```

Stops listening to the step counter sensor

--------------------


### getCurrentSteps()

```typescript
getCurrentSteps() => Promise<StepCounterData>
```

Returns the current step count since device boot

**Returns:** <code>Promise&lt;<a href="#stepcounterdata">StepCounterData</a>&gt;</code>

--------------------


### addListener('step', ...)

```typescript
addListener(eventName: 'step', listenerFunc: (data: StepCounterData) => void) => Promise<PluginListenerHandle>
```

Emits step updates

| Param              | Type                                                                           |
| ------------------ | ------------------------------------------------------------------------------ |
| **`eventName`**    | <code>'step'</code>                                                            |
| **`listenerFunc`** | <code>(data: <a href="#stepcounterdata">StepCounterData</a>) =&gt; void</code> |

**Returns:** <code>Promise&lt;<a href="#pluginlistenerhandle">PluginListenerHandle</a>&gt;</code>

--------------------


### Interfaces


#### StepCounterData

| Prop        | Type                |
| ----------- | ------------------- |
| **`steps`** | <code>number</code> |


#### PluginListenerHandle

| Prop         | Type                                      |
| ------------ | ----------------------------------------- |
| **`remove`** | <code>() =&gt; Promise&lt;void&gt;</code> |

</docgen-api>
