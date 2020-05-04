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

### Object Relation Mapping - Unidirectional

We will discuss three different type of mapping supported by hibernate, which is given below: 

1. Same primary key
2. Foreign key
3. Join table.  

**Same primary key**

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
 
@GeneratedValue(generator= "custom_foreigngen"):- generatior will create custom table to manage id.
@OneToOne :- this annotation is used to map one to one relationship.
Cascade :- Cascase is used to give permision to perform operation type on both Table.
@PrimaryKeyJoinColumn :- PrimaryKeyJoinColumn is used to create same primary key in owned class which is already used in Owner class.

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