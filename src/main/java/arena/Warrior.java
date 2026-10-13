package arena;

public class Warrior extends Hero implements Castable, Restable {
  private final int armor;

  public Warrior(String name, int maxHealth, int baseAttack, int armor) {
    super(name, maxHealth, baseAttack);
    if (armor < MIN_STAT_VALUE) {
      throw new IllegalArgumentException("Броня не может быть меньше " + MIN_STAT_VALUE);
    }
    this.armor = armor;
  }

  @Override
  public void takeDamage(int damage) {
    int finalDamage = damage - this.armor;
    if (finalDamage < 1) {
      finalDamage = 1;
    }
    super.takeDamage(finalDamage);
  }

  @Override
  public void attack(Hero target) {
    System.out.println(this.getName() + " наносит мощный удар мечом по " + target.getName() + "!");
    target.takeDamage(this.getBaseAttack());
  }

  @Override
  public ActionType makeTurn(Hero target) {
    if (canCast()) {
      castSpecialSkill(target);
      return ActionType.SPECIAL_SKILL;
    } else if (needsRest()) {
      rest();
      return ActionType.REST;
    } else {
      attack(target);
      return ActionType.BASE_ATTACK;
    }
  }

  @Override
  public boolean canCast() {
    return this.getHealth() < (this.getMaxHealth() / 2.0);
  }

  @Override
  public void castSpecialSkill(Hero target) {
    if (canCast()) {
      int damage = this.getBaseAttack() + (this.armor * 2);
      System.out.println(this.getName() + " использует Удар щитом против " + target.getName() + "!");
      target.takeDamage(damage);
    } else {
      System.out.println(this.getName() + " попытался ударить щитом, но потерял равновесие!");
    }
  }

  @Override
  public boolean needsRest() {
    return this.getHealth() < (this.getMaxHealth() * 0.15);
  }

  @Override
  public void rest() {
    System.out.println(this.getName() + " открывает Второе дыхание и восстанавливает силы!");
    this.heal(this.armor * 2);
  }
}