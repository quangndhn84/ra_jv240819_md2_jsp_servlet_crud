create database Categories_Management_DB;
use Categories_Management_DB;
create table Categories(
    catalog_id int primary key auto_increment,
    catalog_name varchar(100) not null unique,
    catalog_description text,
    catalog_status bit
);
-- procedure lấy danh sách danh mục
DELIMITER &&
create procedure find_all_categories()
begin
    select * from Categories;
end &&
DELIMITER &&;
-- procedure thêm mới danh mục
DELIMITER &&
create procedure insert_categories(
    name_in varchar(100),
    des_in text,
    status_in bit
)
begin
    insert into Categories(catalog_name, catalog_description, catalog_status)
        values (name_in,des_in,status_in);
end &&
DELIMITER &&;
-- procedure cập nhật danh mục
DELIMITER &&
create procedure update_categories(
    id_in int,
    name_in varchar(100),
    des_in text,
    status_in bit
)
begin
    update Categories
        set catalog_name = name_in,
            catalog_description = des_in,
            catalog_status = status_in
    where catalog_id = id_in;
end &&
DELIMITER &&;
-- Procedure xóa danh mục
DELIMITER &&
create procedure delete_categories(id_in int)
begin
    delete from Categories where catalog_id = id_in;
end &&
DELIMITER &&;
DELIMITER &&
create procedure find_categories_by_id(id_in int)
begin
    select * from Categories where catalog_id = id_in;
end &&
DELIMITER &&;