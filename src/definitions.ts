import type { PluginListenerHandle } from '@capacitor/core';

export interface StepCounterData {
  steps: number;
}

export interface StepCounterPlugin {
  /**
   * Starts listening to the step counter sensor
   */
  start(): Promise<void>;

  /**
   * Stops listening to the step counter sensor
   */
  stop(): Promise<void>;

  /**
   * Returns the current step count since device boot
   */
  getCurrentSteps(): Promise<StepCounterData>;

  /**
   * Emits step updates
   */
  addListener(
    eventName: 'step',
    listenerFunc: (data: StepCounterData) => void
  ): Promise<PluginListenerHandle>;
}
