CREATE TABLE accidents_and_rules (
  accident_id int REFERENCES accidents(id),
  rule_id int REFERENCES rules(id)
);