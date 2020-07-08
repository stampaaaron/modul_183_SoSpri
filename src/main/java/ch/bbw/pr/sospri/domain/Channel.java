package ch.bbw.pr.sospri.domain;

import ch.bbw.pr.sospri.security.auth.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "channel")
public class Channel {

  @Id
  @Column(name = "id")
  @GeneratedValue(generator = "generatorMessage", strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(name = "generatorMessage", initialValue = 10)
  private Long id;

  @Column(name = "name")
  private String name;

  @OneToMany(fetch = FetchType.LAZY)
  @JoinColumn(name = "channel_id")
  private List<Message> messages = new ArrayList<>();

  @ElementCollection
  @Enumerated(EnumType.STRING)
  private List<Role> roles;

}
