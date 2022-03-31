package com.sia.tacos.data;

import java.util.List;

import com.sia.tacos.TacoOrder;
import org.springframework.data.repository.CrudRepository;

//Интерфейс для сохранения заказа, при CrudRepository не нужно делать имплементацию методов
//Такой интерфейс теперь можно просто заинжектить в контроллер (Spring Data API)
public interface OrderRepository extends CrudRepository<TacoOrder, Long> {

    //Java понимает, что мы хотим найти, потому что мы параметризовали CrudRepository с TacoOrder
    //List<TacoOrder> findByDeliveryZip(String deliveryZip);
    //Вообще спринг дата умеет парсить названия методов, главное правильно их составлять
}
