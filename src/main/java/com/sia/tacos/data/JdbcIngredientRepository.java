package com.sia.tacos.data;

//Данный класс не нужен при использовании Spring Data!
/*@Repository //Спрингом класс будет опознан как контейнер, в который будет введёт jdbc template
public class JdbcIngredientRepository implements IngredientRepository {

    private JdbcTemplate jdbcTemplate; //будут создавать для запросов в БД, в методах интерфейса jdbc repository

    @Autowired //Если конструктор один, то можно не объявлять
    public JdbcIngredientRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override //Находит все ингредиенты
    public Iterable<Ingredient> findAll() {
        return jdbcTemplate.query(
                "select id, name, type from Ingredient",
                this::mapRowToIngredient);
    }

    @Override //Находит ингредиент по ID
    public Optional<Ingredient> findById(String id) {
        List<Ingredient> results = jdbcTemplate.query(
                "select id, name, type from Ingredient where id=?", //? - это плейсхолдер для переданного id пользователем
                this::mapRowToIngredient,
                id);

        return results.size() == 0 ?
                Optional.empty() :
                Optional.of(results.get(0));
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        jdbcTemplate.update(
                "insert into Ingredient (id, name, type) values (?, ?, ?)",
                ingredient.getId(),
                ingredient.getName(),
                ingredient.getType().toString());

        return ingredient;
    }

    //Мапит результаты запроса и возвращает объект класса Ингридиент
    private Ingredient mapRowToIngredient(ResultSet row, int rowNum) throws SQLException {
        return new Ingredient(
                row.getString("id"),
                row.getString("name"),
                Ingredient.Type.valueOf(row.getString("type")));
    }
}*/
