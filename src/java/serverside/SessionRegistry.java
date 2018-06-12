/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverside;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.websocket.Session;

/**
 *
 * @author george
 */
@Singleton
public class SessionRegistry {

    private List<Session> ls = new ArrayList<>();

    @Lock(LockType.READ)
    public List<Session> getAll() {
        return Collections.unmodifiableList(ls);
    }

    @Lock(LockType.WRITE)
    public void add(Session session) {
        ls.add(session);
    }

    @Lock(LockType.WRITE)
    public void remove(Session session) {
        ls.remove(session);

    }

}
