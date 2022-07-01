package com.company.domain.models;

// TODO: 30.06.2022 Договор, заключаемый между цехом-заказчиком и цехом-изготовителем
//  на изготовление оснастки

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contract {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "number")
    private String number;

    @Column (name = "date")
    private LocalDate date;

    @Column (name = "task")
    private String task; //предмет договора

    @Column (name = "customer")
    private DevelopmentDepartment customer;// заказчик

    @Column (name = "executor")
    private DevelopmentDepartment executor; //исполнтель
}