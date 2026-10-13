package arena;

public class Mage extends Hero implements Castable, Restable {
  private final int maxMana;
  private int mana;

  public Mage(String name, int maxHealth, int baseAttack, int maxMana) {
    super(name, maxHealth, baseAttack);
    if (maxMana < MIN_STAT_VALUE) {
      throw new IllegalArgumentException("Мана не может быть меньше " + MIN_STAT_VALUE);
    }
    this.maxMana = maxMana;
    this.mana = maxMana;
  }

  @Override
  public void attack(Hero target) {
    if (this.mana >= 10) {
      System.out.println(this.getName() + " запускает Магический выстрел в " + target.getName() + "!");
      target.takeDamage(this.getBaseAttack() * 2);
      this.mana -= 10;
    } else {
      System.out.println(this.getName() + " бьет посохом " + target.getName() + " и восстанавливает немного маны.");
      target.takeDamage(this.getBaseAttack() / 2);
      this.mana = Math.min(this.maxMana, this.mana + 5);
    }
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
    return this.mana >= 25;
  }

  @Override
  public void castSpecialSkill(Hero target) {
    if (canCast()) {
      System.out.println(this.getName() + " обрушивает Огненную глыбу на " + target.getName() + "!");
      target.takeDamage(this.getBaseAttack() * 3);
      this.mana -= 25;
    } else {
      System.out.println(this.getName() + " пытается скастовать Огненную глыбу, но заклинание срывается из-за нехватки маны!");
    }
  }

  @Override
  public boolean needsRest() {
    return this.mana == 0 || this.getHealth() < (this.getMaxHealth() * 0.3);
  }

  @Override
  public void rest() {
    System.out.println(this.getName() + " погружается в Исцеляющую медитацию!");
    this.mana = this.maxMana;
    this.heal(this.getBaseAttack());
  }
}