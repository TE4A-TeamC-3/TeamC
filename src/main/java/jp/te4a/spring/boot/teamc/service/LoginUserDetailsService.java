package jp.te4a.spring.boot.teamc.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jp.te4a.spring.boot.teamc.bean.UserBean;
import jp.te4a.spring.boot.teamc.repository.UserRepository;
import jp.te4a.spring.boot.teamc.security.LoginUserDetails;

@Service
public class LoginUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    // ユーザ名を指定してDBからユーザ情報取得 認証用
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserBean> opt = userRepository.findByUsername(username);
        UserBean userBean = opt.orElseThrow(() -> new UsernameNotFoundException("The requested user is not found."));
        return new LoginUserDetails(userBean, true, true, true, getAuthorities(userBean));  // 修正: userBeam -> user
    }

    private Collection<GrantedAuthority> getAuthorities(UserBean userBean) {
        String role = userBean.getRole();
        if ("ADMIN".equals(role)) {
            return AuthorityUtils.createAuthorityList("ROLE_ADMIN", "ROLE_USER");
        } else if ("USER".equals(role)) {
            return AuthorityUtils.createAuthorityList("ROLE_USER");
        } else {
            throw new IllegalArgumentException("Invalid role: " + role);
        }
    }    
    /*@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserBean> opt = userRepository.findByUsername(username);
        UserBean userBean = opt.orElseThrow(() -> new UsernameNotFoundException("The requested user is not found."));
        return new org.springframework.security.core.userdetails.User(
                userBean.getUsername(),
                userBean.getPassword(),
                getAuthorities(userBean)
        );
    }
    }*/
}
