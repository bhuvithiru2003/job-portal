package com.jsp.job_portal.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Recruiter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Integer id;
 @Size(min=3, max=30, message= " * Enter Between 3 ~ 30 Character")
 private String name;
 @Email(message = "* Enter Proper Email")
 @NotEmpty(message = "* It is Required Field")
 private String email;
 @DecimalMin(value = "6000000000", message = "* Enter Proper Mobile Number")
 @DecimalMax(value = "9999999999", message = "* Enter Proper Mobile Number")
 @NotEmpty(message = "* It is Required Field")
 private Long mobile;
 @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{4,12}$" ,message = "* Enter Maximum * Character")
private String password;
@Transient
@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{4,12}$" ,message = "* Enter Maximum * Character")
private String confirmPassword;
@NotEmpty(message = "* It is Required Field")
private String companyName;
private Boolean verified;
private Integer otp;
    
}
