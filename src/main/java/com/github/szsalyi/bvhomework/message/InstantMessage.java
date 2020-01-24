package com.github.szsalyi.bvhomework.message;

import com.github.szsalyi.bvhomework.user.User;
import lombok.*;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServer;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class InstantMessage {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @ManyToOne
    @JoinColumn(name="fromMessages", insertable = false, updatable = false)
    private User fromUser;

    @NonNull

    @ManyToOne
    @JoinColumn(name="toMessages", insertable = false, updatable = false)
    private User toUser;

    @NonNull
    @NotBlank
    @Size(max = 500)
    @Column(name = "content", nullable = false)
    private String content;

    //private String chatRoomId;

    public boolean isPublic() {
        return toUser == null;
    }
}
