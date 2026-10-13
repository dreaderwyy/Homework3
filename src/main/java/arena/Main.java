package arena;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Добро пожаловать в симулятор «Арена Героев»!");

    Hero hero1 = createHero(scanner, 1);
    Hero hero2 = createHero(scanner, 2);

    Arena arena = new Arena(hero1, hero2);
    arena.startTournament();

    scanner.close();
  }

  private static Hero createHero(Scanner scanner, int heroNumber) {
    System.out.println("\nСоздание бойца №" + heroNumber);
    Hero hero = null;

    while (hero == null) {
      try {
        System.out.println("Выберите тип персонажа (1 - Воин, 2 - Маг): ");
        String typeStr = scanner.nextLine();

        System.out.println("Введите имя:");
        String name = scanner.nextLine();

        System.out.println("Введите максимальное здоровье (HP):");
        int maxHealth = Integer.parseInt(scanner.nextLine());

        System.out.println("Введите базовую атаку:");
        int baseAttack = Integer.parseInt(scanner.nextLine());

        if ("1".equals(typeStr)) {
          System.out.println("Введите показатель брони:");
          int armor = Integer.parseInt(scanner.nextLine());
          hero = new Warrior(name, maxHealth, baseAttack, armor);
        } else if ("2".equals(typeStr)) {
          System.out.println("Введите максимальный запас маны:");
          int maxMana = Integer.parseInt(scanner.nextLine());
          hero = new Mage(name, maxHealth, baseAttack, maxMana);
        } else {
          System.out.println("Ошибка: Выбран неизвестный тип. Введите 1 или 2.");
        }
      } catch (NumberFormatException e) {
        System.out.println("Ошибка: Ожидается ввод числа для числовых характеристик. Повторите попытку.");
      } catch (IllegalArgumentException e) {
        System.out.println("Ошибка валидации: " + e.getMessage() + ". Повторите попытку.");
      }
    }
    return hero;
  }
}