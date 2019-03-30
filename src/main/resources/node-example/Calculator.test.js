
const Calculator = require('./Calculator');

test('Calculator can add numbers', () => {

    const calc = new Calculator();
    calc.add(2);
    calc.add(3);

    expect(calc.value).toBe(5);
});

test('Calculator can subtract numbers', () => {

    const calc = new Calculator();
    calc.add(6);
    calc.subtract(2);

    expect(calc.value).toBe(4);
});