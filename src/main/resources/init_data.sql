--basket products
insert into PRODUCT (product_id, product_code, product_name, price_per_unit, measurement_unit) 
values (1, 'SOUP', 'Soup' , 0.65, 'TIN');
insert into PRODUCT (product_id, product_code, product_name, price_per_unit, measurement_unit) 
values (2, 'BRED', 'Bread' , 0.80, 'LOAF');
insert into PRODUCT (product_id, product_code, product_name, price_per_unit, measurement_unit) 
values (3, 'MILK', 'Milk' , 1.30, 'BOTTLE');
insert into PRODUCT (product_id, product_code, product_name, price_per_unit, measurement_unit) 
values (4, 'APPL', 'Apples' , 1.00, 'BAG');

insert into DISCOUNTED_PRODUCT_CONFIG (discount_product_config_id, product_id, discounted_product_qty, discount_multiplier)
values (1, 4, 1, '0.1');

insert into DISCOUNT_RULE (discount_rule_id, is_active, single_product_discount, product_id, eligibility_qty, discount_product_config_id, discount_text)
values (1, TRUE, TRUE , 4, 1, 1, 'Apples at 10% off');
--
insert into DISCOUNTED_PRODUCT_CONFIG (discount_product_config_id, product_id, discounted_product_qty, discount_multiplier)
values (2, 3, 1, '0.05');

insert into DISCOUNT_RULE (discount_rule_id, is_active, single_product_discount, product_id, eligibility_qty, discount_product_config_id, discount_text)
values (2, TRUE, TRUE , 3, 1, 2, 'Milk at 5% off');

--Combination Discount

insert into DISCOUNTED_PRODUCT_CONFIG (discount_product_config_id, product_id, discounted_product_qty, discount_multiplier)
values (3, 2, 1, '0.5');

insert into DISCOUNT_RULE (discount_rule_id, is_active, single_product_discount, product_id, eligibility_qty, discount_product_config_id, discount_text)
values (3, TRUE, FALSE , 1, 2, 3, 'Bread loaf at 50% on 2 tins of soup');