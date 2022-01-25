package cn.tgkzxy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Admin {
    private int aid;
    private String name;
    private String password;
    private String token;
}
