package arena;

public abstract class Hero {
  public static final int MIN_STAT_VALUE = 1;

  private final String name;
  private final int maxHealth;
  private final int baseAttack;
  private int health;

  public Hero(String name, int maxHealth, int baseAttack) {
    if (maxHealth < MIN_STAT_VALUE || baseAttack < MIN_STAT_VALUE) {
      throw new IllegalArgumentException("Числовые характеристики не могут быть меньше " + MIN_STAT_VALUE);
    }
    this.name = name;
    this.maxHealth = maxHealth;
    this.baseAttack = baseAttack;
    this.health = maxHealth;
  }

  public String getName() { return name; }
  public int getMaxHealth() { return maxHealth; }
  public int getBaseAttack() { return baseAttack; }
  public int getHealth() { return health; }

  public abstract void attack(Hero target);
  public abstract ActionType makeTurn(Hero target);

  public void takeDamage(int damage) {
    this.health -= damage;
    if (this.health <= 0) {
      this.health = 0;
      System.out.println(this.name + " пал в бою!");
    }
  }

  public void heal(int amount) {
    this.health += amount;
    if (this.health > this.maxHealth) {
      this.health = this.maxHealth;
    }
  }

  public boolean isAlive() {
    return this.health > 0;
  }
}