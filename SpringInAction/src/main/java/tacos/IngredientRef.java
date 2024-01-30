package tacos;

import org.springframework.data.cassandra.core.mapping.Table;

import lombok.Data;

@Data
@Table
public class IngredientRef {
	private final String ingredient;
}
