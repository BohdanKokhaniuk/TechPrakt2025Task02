# lab-1/main.py
from datetime import date

class Money:
    def __init__(self, whole: int, cents: int):
        self.whole = whole
        self.cents = cents

    def __str__(self):
        return f"{self.whole}.{str(self.cents).zfill(2)} грн"

    def set_value(self, whole: int, cents: int):
        self.whole = whole
        self.cents = cents

    def decrease(self, amount):
        total_cents = self.whole * 100 + self.cents - amount.whole * 100 - amount.cents
        self.whole = total_cents // 100
        self.cents = total_cents % 100


class Product:
    def __init__(self, name: str, price: Money):
        self.name = name
        self.price = price

    def decrease_price(self, amount: Money):
        self.price.decrease(amount)

    def __str__(self):
        return f"{self.name}: {self.price}"


class WarehouseItem:
    def __init__(self, product: Product, unit: str, quantity: int, last_delivery: date):
        self.product = product
        self.unit = unit
        self.quantity = quantity
        self.last_delivery = last_delivery

    def __str__(self):
        return f"{self.product.name}, {self.quantity} {self.unit}, ціна: {self.product.price}, останнє постачання: {self.last_delivery}"


class Warehouse:
    def __init__(self):
        self.items = []

    def add_item(self, item: WarehouseItem):
        self.items.append(item)

    def remove_item(self, product_name: str, quantity: int):
        for item in self.items:
            if item.product.name == product_name:
                item.quantity -= quantity

    def inventory_report(self):
        return [str(item) for item in self.items]


class Reporting:
    def __init__(self, warehouse: Warehouse):
        self.warehouse = warehouse

    def income_invoice(self, item: WarehouseItem):
        self.warehouse.add_item(item)
        print(f"Постачання: {item}")

    def outcome_invoice(self, product_name: str, quantity: int):
        self.warehouse.remove_item(product_name, quantity)
        print(f"Відвантаження: {product_name}, {quantity} одиниць")

    def inventory_report(self):
        print("Звіт інвентаризації:")
        for line in self.warehouse.inventory_report():
            print(line)


# main method for testing
def main():
    warehouse = Warehouse()
    reporting = Reporting(warehouse)

    price1 = Money(100, 50)
    product1 = Product("Ноутбук", price1)
    item1 = WarehouseItem(product1, "шт", 10, date(2025, 6, 1))
    reporting.income_invoice(item1)

    price2 = Money(25, 99)
    product2 = Product("Миша", price2)
    item2 = WarehouseItem(product2, "шт", 50, date(2025, 6, 3))
    reporting.income_invoice(item2)

    reporting.outcome_invoice("Миша", 5)

    reporting.inventory_report()

if __name__ == "__main__":
    main()
