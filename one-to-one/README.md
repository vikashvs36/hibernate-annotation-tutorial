# One-To-One relationship

This project is created for hibernate object relation mapping through annotation. We can learn one to one object relationship using Annotation in this project.
The one-to-one jpa annotation is used to map the source entity with the target entity.

**Add Dependency**

There are some dependency which is added in pom.xml file. Yes, I am using maven dependency, you can use as your comfort.

* Spring JPA 
* MySQL Database
* Lombok

**application.yml**

Here I am using YAML file you can use as your comfort. MySQL and log configuration is mention in the application.yml file.

    spring:
      datasource:
        url: jdbc:mysql://localhost:3306/one-to-one?createDatabaseIfNotExist=true
        username: root
        password: root
      jpa:
        hibernate:
          ddl-auto: create
        show-sql: true  

As you can see, I am not using spring-web dependency so I am using CommandLineRunner.

    @SpringBootApplication
    public class OneToOneApplication implements CommandLineRunner {
    
    	public static void main(String[] args) {
    		SpringApplication.run(OneToOneApplication.class, args);
    	}
    
    	@Override
    	public void run(String... args) throws Exception {
    		System.out.println("We can get output from here");
    	}
    }

### Object Relation Mapping

We will discuss three different type of mapping supported by hibernate, which is given below: 

1. Same primary key
2. Foreign key
3. Join table.  

### Same primary key

**Same primary key - Unidirectional**

In this approach, Hibernate will insure that it will use a common primary key value in both the table.

![](img/onetoone-samePk.png)

> User class

    @Entity
    public class User {
        @Id
        @GeneratedValue(generator= "custom_foreigngen")
        private Long id;
        private String username, password;
        
        @OneToOne(cascade = CascadeType.ALL)
        @PrimaryKeyJoinColumn
        private Address address;
        
    }        
 
**@GeneratedValue(generator= "custom_foreigngen"):-** generatior will create custom table to manage id.

**@OneToOne :-** this annotation is used to map one to one relationship.

**Cascade :-** Cascase is used to give permision to perform operation type on both Table.

**@PrimaryKeyJoinColumn :-** PrimaryKeyJoinColumn is used to create same primary key in owned class which is already used in Owner class.

> Address class

    @Entity
    public class Address {
        @Id
        @GeneratedValue
        private Long id;
        private String state;
    }
    
If you want to save User and address below code will there:

    User user = new User("Vikash", "singh", new Address("Delhi"));
    userDao.save(user);   // userDao is JPARepository of User class.
    
After run this application below query will be perform by hibernate:

> Create table structure :

    Hibernate: create table address (id bigint not null, state varchar(255), primary key (id)) engine=InnoDB
    Hibernate: create table custom_foreigngen (next_val bigint) engine=InnoDB
    Hibernate: insert into custom_foreigngen values ( 1 )
    Hibernate: create table hibernate_sequence (next_val bigint) engine=InnoDB
    Hibernate: insert into hibernate_sequence values ( 1 )   
    Hibernate: create table user (id bigint not null, password varchar(255), username varchar(255), primary key (id)) engine=InnoDB

> Insert data into tables :

    Hibernate: select next_val as id_val from custom_foreigngen for update
    Hibernate: update custom_foreigngen set next_val= ? where next_val=?
    Hibernate: select next_val as id_val from hibernate_sequence for update
    Hibernate: update hibernate_sequence set next_val= ? where next_val=?
    Hibernate: insert into address (state, id) values (?, ?)
    Hibernate: insert into user (password, username, id) values (?, ?, ?)
    
### Unidirectional - output

As log of the console, if you are going to find user then hibernate will get address as well because there are a relation 
between user and address which is given below :   

    // hibernate log
    Hibernate: select user0_.id as id1_1_0_, user0_.password as password2_1_0_, user0_.username as username3_1_0_ from user user0_ where user0_.id=?
    Hibernate: select address0_.id as id1_0_0_, address0_.state as state2_0_0_ from address address0_ where address0_.id=?
    
    // find user by id
    User(id=1, username=Vikash, password=singh, address=Address(id=1, state=Delhi))
    
But if you find address then user will not come as given below because there are not relation from Address to User object. I mean, 
there is only one Unidirectional mapping so need to map bidirectional as well.

     Hibernate: select address0_.id as id1_0_0_, address0_.state as state2_0_0_ from address address0_ where address0_.id=?
     Address : Address(id=2, state=Noida)
     
**Same primary key - Bidirectional**

The term “bidirectional” literally means “functioning in two directions”,  it means that we are able to access Object A 
from Object B, and Object B from Object A. and we will able to operate all operation from both side. Till now, We are able 
to get address object from user side but Address is unknown that associated from which User.

For access User from Address following annotation needs to integrate:

    @Entity
    public class Address {
        @Id
        @GeneratedValue
        private Long id;
        private String state;
    
        @OneToOne(mappedBy = "address", cascade = CascadeType.ALL)
        private User user;
    }  
  
Need to understand *@OneToOne(mappedBy = "address", cascade = CascadeType.ALL).* 

**@OneToOne :** @OneToOne annotation is need to mark on User type property in Address object. 

**mappedBy :** The value of mappedBy is the name of the association-mapping attribute on the owning side.  
mappedBy attribute are always put(annotated) on the inverse side of relation ship 

**cascade :** Cascade is mapped for which type of operation wants to operate from Address side.

**Note :** If cascade is not mentioning here, It will not give all permission to operate all operations from 
bidirectional like delete. 

> Output

    // Hibernate log
    Hibernate: select address0_.id as id1_0_0_, address0_.state as state2_0_0_, user1_.id as id1_1_1_, user1_.password as password2_1_1_, user1_.username as username3_1_1_ from address address0_ left outer join user user1_ on address0_.id=user1_.id where address0_.id=?
    // Address Object log
    Address : Address{id=2, state='Noida'}, User : User{id=2, username='Anil', password='gupta'}

### Foreign key

Hibernate one-to-one mapping with foreign key association. In this type of association, a foreign key column is created 
in owner entity. 

This is an example of a one-to-one relationship, in this case between Book and Book_details entities.

![](img/ontoone-foreignKey.png)

#### Implementing with a Foreign Key - Unidirectional

First, let's create the Book class and annotate it appropriately:

    @Entity
    public class Book {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String name;
        @OneToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "bookDetails_id")
        private BookDetail bookDetail;
        
        // getter, setter and contructor
     }
 
The Book_detail entity create as simple Pojo class:

    @Entity
    @Table(name = "book_detail")
    public class BookDetail {
        @Id
        @GeneratedValue
        private Long id;
        private int numberOfPages;
        
        // Setter, getter and Constructor
    }
    
**@OneToOne :** Defines a one-to-one relationship between 2 entities.

**cascade :** Cascade is mapped for which type of operation wants to operate from Address side.

**@JoinColumn :** defines foreign key column and indicates the owner of the relationship.

**unique = true :** enforces the unique constraint, 1 book belongs to only 1 bookDetails object. If value of unique is 
true then *many-to-one* works like *one-to-one* and *many-to-many* works like *one-to-many*
    
> Output

    // ########### Save() ###########  
    Hibernate: insert into book_detail (number_of_pages) values (?)
    Hibernate: insert into book (book_details_id, name) values (?, ?)
    
    // ########### FindBookById() ###########
    Hibernate: select book0_.id as id1_1_0_, book0_.book_details_id as book_det3_1_0_, book0_.name as name2_1_0_, bookdetail1_.detail_id as detail_i1_2_1_, bookdetail1_.number_of_pages as number_o2_2_1_ from book book0_ left outer join book_detail bookdetail1_ on book0_.book_details_id=bookdetail1_.detail_id where book0_.id=?
    Book{id=1, name='SCJP', bookDetail=BookDetail{id=1, numberOfPages=870}}
    
    // ########### delete() ###########
    Hibernate: select book0_.id as id1_1_0_, book0_.book_details_id as book_det3_1_0_, book0_.name as name2_1_0_, bookdetail1_.detail_id as detail_i1_2_1_, bookdetail1_.number_of_pages as number_o2_2_1_ from book book0_ left outer join book_detail bookdetail1_ on book0_.book_details_id=bookdetail1_.detail_id where book0_.id=?
    Book{id=2, name='K.C.SINHA', bookDetail=BookDetail{id=2, numberOfPages=435}}
    Hibernate: delete from book where id=?
    Hibernate: delete from book_detail where detail_id=?


#### Implementing with a Foreign Key - Bidirectional

Just should be mentioned of relation on the only owned entity like given blow of bookDetails entity:

    @Entity
    @Table(name = "book_detail")
    public class BookDetail {
    
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "detail_id")
        private Long id;
        private int numberOfPages;
    
        // name of mappedBy (bookDetails) is same as properties of BookDetails Object which is present in Book Object
        @OneToOne(mappedBy = "bookDetail", cascade = CascadeType.ALL)
        private Book book; 
        
        // Construcotor, Getter and Setter.
    } 
    
**mappedBy :** mappedBy indicates the inverse of the relationship.

**cascade :** Cascade is mapped for which type of operation wants to operate from BookDetails entity side.

**Note :** If cascade is not mentioning here, It will not give all permission to operate all operations from 
bidirectional like delete. 

if the name of id column is different then need to mention on the owner side as well.

    @Entity
    public class Book {
    
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String name;
        @OneToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "details_id", unique = true)
        private BookDetail bookDetail;
        
        // Constructor, Getter and Setter.
    }

**@OneToOne :** Defines a one-to-one relationship between 2 entities.

**cascade :** Cascade is mapped for which type of operation wants to operate from  BookDetails entity  side.

**unique = true :** enforces the unique constraint, 1 book belongs to only 1 bookDetails object. If value of unique is 
true then *many-to-one* works like *one-to-one* and *many-to-many* works like *one-to-many*

**Note :** name of mappedBy (bookDetails) is same as properties name of BookDetails Object which is present in Book Object

> Output 

    // ########## FindById(1) ##########
    Hibernate: select bookdetail0_.detail_id as detail_i1_2_0_, bookdetail0_.number_of_pages as number_o2_2_0_, book1_.id as id1_1_1_, book1_.details_id as details_3_1_1_, book1_.name as name2_1_1_ from book_detail bookdetail0_ left outer join book book1_ on bookdetail0_.detail_id=book1_.details_id where bookdetail0_.detail_id=?
    BookDetail{id=1, numberOfPages=870}, Book{id=1, name='SCJP', bookDetail=BookDetail{id=1, numberOfPages=870}}
    
    // ########## delete(1) ##########
    BookDetail{id=1, numberOfPages=870}, Book{id=1, name='SCJP', bookDetail=BookDetail{id=1, numberOfPages=870}}
    Hibernate: select bookdetail0_.detail_id as detail_i1_2_0_, bookdetail0_.number_of_pages as number_o2_2_0_, book1_.id as id1_1_1_, book1_.details_id as details_3_1_1_, book1_.name as name2_1_1_ from book_detail bookdetail0_ left outer join book book1_ on bookdetail0_.detail_id=book1_.details_id where bookdetail0_.detail_id=?
    Hibernate: delete from book where id=?
    Hibernate: delete from book_detail where detail_id=?
    
    //  ########## FindAll() ##########
    Hibernate: select bookdetail0_.detail_id as detail_i1_2_, bookdetail0_.number_of_pages as number_o2_2_ from book_detail bookdetail0_
    Hibernate: select book0_.id as id1_1_1_, book0_.details_id as details_3_1_1_, book0_.name as name2_1_1_, bookdetail1_.detail_id as detail_i1_2_0_, bookdetail1_.number_of_pages as number_o2_2_0_ from book book0_ left outer join book_detail bookdetail1_ on book0_.details_id=bookdetail1_.detail_id where book0_.details_id=?
    BookDetail{id=3, numberOfPages=435}, Book{id=3, name='STUDENT FRIENDS', bookDetail=BookDetail{id=3, numberOfPages=435}}
