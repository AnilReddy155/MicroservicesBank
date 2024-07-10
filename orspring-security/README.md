
**Basic Authentication:**
    https://github.com/eazybytes/springsecurity6

1. Write one API which returns String and add security dependency.
2. Run the application, You can see the password created in consol. default user us user.
3. Access the Url, it wont work becouse spring boot configured with basic security.
4. Provide user and password in Basic Auth. it will work.
5. We can pass the Base64 tocken in Header also using Autherization :  Basic token
6. Token you can generate by visiting the https://www.base64encode.org/ or (echo -n user:93a01cf0-794b-4b98-86ef-54860f36f7f3 | base64) with the formate of user:password.

![alt text](image.png)


**To Enable Https:**

	Windows:
	 
	winpty openssl req -newkey rsa:2048 -x509 -keyout key.pem -out cert.pem -days 365
	winpty openssl pkcs12 -export -in cert.pem -inkey key.pem -out certificate.p12 -name "certificate"
	 
	Linux: 
	openssl req -newkey rsa:2048 -x509 -keyout key.pem -out cert.pem -days 365
	openssl pkcs12 -export -in cert.pem -inkey key.pem -out certificate.p12 -name "certificate"

	copy the certificate.p12 file to resource and add below properties in prop file.
	server.ssl.key-store-type=PKCS12
	server.ssl.key-store=classpath:certificate.p12
	server.ssl.key-store-password=12345 // this is the password you given when executing above commands
 

 **Some Spring Security Configurations**
    SpringBootWebSecurityConfiguration : which have a configuration for basic configration. (SecurityFilterChain) will be returned as bean.
    
    @Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((requests) -> requests.requestMatchers("/hello").authenticated().requestMatchers("/demo").permitAll());
		http.formLogin(withDefaults());
		http.httpBasic(withDefaults());
		return http.build();
	}

 **Spring default schema tables structure**
    create table users(
        username varchar(50) not null primary key,
        password varchar(50) not null,
        enabled boolean not null
    );

    create table authorities (
        username varchar(50) not null,
        authority varchar(50) not null,
        constraint fk_authorities_users foreign key(username) references users(username)
    );
    create unique index ix_auth_username on authorities (username,authority);

    insert into users values('Suneel', '12345', '1');

    insert into authorities values ('Suneel', 'write');   

    **Code**
     @Bean
	UserDetailsService jdbcUserDetailsService(DataSource dataSource) {
		return new JdbcUserDetailsManager(dataSource);
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

**Custome Schema Configuration**
    `create table sec.customer (
        id bigint not null,
        email varchar(255),
        password varchar(255),
        role varchar(255),
        primary key (id)
    )
	
	insert into sec.customer values (1, 'anil@email.com', 123, 'read');`

    **Code**

    package com.cg.security.service;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.security.core.authority.SimpleGrantedAuthority;
    import org  .springframework.security.core.userdetails.User;
    import org.springframework.security.core.userdetails.UserDetails;
    import org.springframework.security.core.userdetails.UserDetailsService;
    import org.springframework.security.core.userdetails.UsernameNotFoundException;
    import org.springframework.stereotype.Service;

    import com.cg.security.entity.Customer;
    import com.cg.security.repository.CustomerRepo;

    @Service
    public class CustomUserDetailsServiceImpl implements UserDetailsService{

        @Autowired
        private CustomerRepo customerRepo;
        
        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            Customer customer = customerRepo.findByEmail(username);
            if(customer == null) {
                throw new UsernameNotFoundException("User Not found");
            }
            return User.withUsername(customer.getEmail()).password(customer.getPassword())
            .authorities(new SimpleGrantedAuthority(customer.getRole())).build();
        }

    }

**Password Encripting Logic**

    Define the BCryptPasswordEncoder as bean as it is recomended by the Spring and strong hashing technique is used.

    @Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

    While we are saving the password into DB we need to encode the password and need to store.