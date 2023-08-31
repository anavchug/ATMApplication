class Transaction {
    constructor(type, amount, localDateTime) {
        this.type = type;
        this.amount = amount;
        this.localDateTime = localDateTime;
    }

    getType() {
        return this.type;
    }

    getAmount() {
        return this.amount;
    }

    getLocalDateTime() {
        return this.localDateTime;
    }
}
