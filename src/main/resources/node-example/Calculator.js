console.log("Hello World");

class Calculator {

    constructor() {
        this.value = 0;
    }

    add(amount) {
        this.value += amount;
    }

    subtract(amount) {
        this.value -= amount;
    }
}

module.exports = Calculator; // exporting calculator