package cn.tgkzxy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Message {
    private int mid;
    private int cid;
    private long time;
    private String message;
    private String mail;
    private String touristName;
}
