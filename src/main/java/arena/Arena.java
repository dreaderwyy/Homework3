package arena;

public class Arena {
  private final Hero fighter1;
  private final Hero fighter2;
  private int roundCounter;

  public Arena(Hero fighter1, Hero fighter2) {
    if (fighter1 == null || fighter2 == null) {
      throw new IllegalArgumentException("Бойцы не могут быть null");
    }
    this.fighter1 = fighter1;
    this.fighter2 = fighter2;
    this.roundCounter = 0;
  }

  public void startTournament() {
    System.out.println("\n=== Турнир начинается! " + fighter1.getName() + " против " + fighter2.getName() + " ===");

    while (fighter1.isAlive() && fighter2.isAlive()) {
      this.roundCounter++;
      System.out.println("\n--- Раунд " + this.roundCounter + " ---");

      Hero attacker;
      Hero defender;

      if (Math.random() < 0.5) {
        attacker = fighter1;
        defender = fighter2;
      } else {
        attacker = fighter2;
        defender = fighter1;
      }

      ActionType firstAction = attacker.makeTurn(defender);
      System.out.println("Игрок " + attacker.getName() + " совершил действие: " + firstAction.getDescription());

      if (!defender.isAlive()) {
        break;
      }

      ActionType secondAction = defender.makeTurn(attacker);
      System.out.println("Игрок " + defender.getName() + " совершил действие: " + secondAction.getDescription());

      printStatus();
    }

    System.out.println("\n=== Бой окончен ===");
    if (fighter1.isAlive()) {
      System.out.println("Победитель: " + fighter1.getName() + "!");
    } else if (fighter2.isAlive()) {
      System.out.println("Победитель: " + fighter2.getName() + "!");
    }
  }

  private void printStatus() {
    System.out.println("[Статус] " + fighter1.getName() + " HP: " + fighter1.getHealth() + "/" + fighter1.getMaxHealth());
    System.out.println("[Статус] " + fighter2.getName() + " HP: " + fighter2.getHealth() + "/" + fighter2.getMaxHealth());
  }
}