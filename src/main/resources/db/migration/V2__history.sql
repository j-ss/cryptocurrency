create table if not exists crypto_history(
    id bigint primary key auto_increment,
    crypto_id bigint not null,
    created_at timestamp default current_timestamp,
    crypto_type varchar(50) not null,
    current_price decimal not null,
    volume bigint,
    market_cap bigint,
    action varchar(50) not null
);