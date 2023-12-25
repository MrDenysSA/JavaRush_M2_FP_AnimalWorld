package org.DenysSyrotiuk;

public abstract class Animal {
    private String icon;    // Іконка
    private int weight;     // Вага цієї однієї тварини, кг
    private int maxAmount;  // Максимальна кількість тварин цього виду на одній клітинці
    private int speed;      // Швидкість переміщення, не більше ніж клітинок за хід
    private int maxFood;    // Скільки кілограмів їжі потрібно тварині для повного насичення

    private boolean isAlive = true; // TODO: якщо false то тваринка вибуває і видаляється
    private int maxWeight;
    private int health;  // TODO: шкала зроровʼя (Ситність) . 1 клітинка переміщення -5 к життю.



    // Вовк має вагу 50 кг
    // Життя 100 ( за кожен такт тваринка втрачає життя ( health - (5 * speed) )
    // Вовск втрачає за такт 15 рунктів життя.
    // якщо в клітинці є хдобіч то тварика не втрачає житття.

}
