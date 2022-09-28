package xie.hailiang.resourceserver.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CUSTOMER")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
	
	@Id
    @GeneratedValue
    @Column(name = "ID", nullable = false, updatable = false)
    private Long id;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;
    
    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "EMAIL", nullable = false)
    private String email;
    
    @Column(name = "PHONE", nullable = false)
    private String phone;
    
    @Column(name = "STATUS", nullable = false)
    private Integer status;
}

