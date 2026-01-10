import { WebPlugin } from '@capacitor/core';
import type { StepCounterPlugin, StepCounterData } from './definitions';

export class StepCounterWeb extends WebPlugin implements StepCounterPlugin {
  private steps = 0;
  private interval?: number;

  async start(): Promise<void> {
    this.interval = window.setInterval(() => {
      this.steps += Math.floor(Math.random() * 3);
      this.notifyListeners('step', { steps: this.steps });
    }, 2000);
  }

  async stop(): Promise<void> {
    if (this.interval) {
      clearInterval(this.interval);
      this.interval = undefined;
    }
  }

  async getCurrentSteps(): Promise<StepCounterData> {
    return { steps: this.steps };
  }
}
