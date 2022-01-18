create table if not exists crypto_currency(
    id bigint primary key auto_increment,
    crypto_type varchar(50) not null,
    current_price decimal not null,
    lowest_price decimal not null,
    highest_price decimal not null,
    volume bigint,
    market_cap bigint,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);