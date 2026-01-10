import { StepCounter } from 'step-counter';

window.testEcho = () => {
    const inputValue = document.getElementById("echoInput").value;
    StepCounter.echo({ value: inputValue })
}
