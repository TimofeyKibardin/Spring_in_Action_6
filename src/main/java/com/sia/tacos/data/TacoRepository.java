package com.sia.tacos.data;

import com.sia.tacos.Taco;
import org.springframework.data.repository.CrudRepository;

public interface TacoRepository extends CrudRepository<Taco, Long> {
}
