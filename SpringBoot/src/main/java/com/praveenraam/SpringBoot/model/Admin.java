package com.praveenraam.SpringBoot.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "admins")
public class Admin extends User{

}
