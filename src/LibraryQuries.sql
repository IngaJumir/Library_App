select * from books where id = 24951;

select id from books
join book_categories
on books.book_category_id = book_categories.id
where name = 'Action and Adventure';

select * from users where id = 13755;
select full_name,email,password,user_group_id,status,start_date,end_date,address from users where id = ''