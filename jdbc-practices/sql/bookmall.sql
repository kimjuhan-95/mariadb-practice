create table user (
    no bigint primary key auto_increment,
    name varchar(100) not null,
    email varchar(200) not null,
    password varchar(100) not null,
    phone varchar(20)
);

create table category (
    no bigint primary key auto_increment,
    name varchar(100) not null
);

create table book (
    no bigint primary key auto_increment,
    title varchar(200) not null,
    price int not null,
    category_no bigint not null,
    foreign key(category_no) references category(no)
);

create table cart (
    user_no bigint not null,
    book_no bigint not null,
    quantity int not null,
    primary key(user_no, book_no),
    foreign key(user_no) references user(no),
    foreign key(book_no) references book(no)
);

create table orders (
    no bigint primary key auto_increment,
    number varchar(50) not null,
    payment int not null,
    shipping varchar(200),
    status varchar(50),
    user_no bigint not null,
    foreign key(user_no) references user(no)
);

create table order_book (
    order_no bigint not null,
    book_no bigint not null,
    quantity int not null,
    price int not null,
    primary key(order_no, book_no),
    foreign key(order_no) references orders(no),
    foreign key(book_no) references book(no)
);