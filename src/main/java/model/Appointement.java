package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder



@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appointement {
    private int id;
    private String time;
    private Service service;
    private Master master;
    private String name;
    private String phoneNumber;
    private String email;



}
