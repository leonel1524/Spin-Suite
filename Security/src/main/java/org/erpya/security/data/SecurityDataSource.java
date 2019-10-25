/*************************************************************************************
 * Product: Spin-Suite (Mobile Suite)                       		                 *
 * Copyright (C) 2012-2018 E.R.P. Consultores y Asociados, C.A.                      *
 * Contributor(s): Yamel Senih ysenih@erpya.com				  		                 *
 * This program is free software: you can redistribute it and/or modify              *
 * it under the terms of the GNU General Public License as published by              *
 * the Free Software Foundation, either version 3 of the License, or                 *
 * (at your option) any later version.                                               *
 * This program is distributed in the hope that it will be useful,                   *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of                    *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the                     *
 * GNU General Public License for more details.                                      *
 * You should have received a copy of the GNU General Public License                 *
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.            *
 ************************************************************************************/
package org.erpya.security.data;
import org.erpya.base.access.AccessService;
import org.erpya.base.access.EnrollmentService;
import org.erpya.security.data.model.LoggedInUser;
import org.erpya.security.data.model.RegisteredUser;
import org.spin.grpc.util.Session;
import org.spin.grpc.util.User;

import java.io.IOException;


/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class SecurityDataSource {

    /**
     * Login user
     * @param username
     * @param password
     * @return
     */
    public Result<LoggedInUser> login(String username, String password) {
        try {
            Session session = AccessService.getInstance().requestLoginDefault(username, password, null);
            if(session != null) {
                LoggedInUser fakeUser =
                        new LoggedInUser(session.getUserInfo().getName(),
                                session.getUserInfo().getName(), session.getUuid());
                AccessService.getInstance().closeServiceProvider();
                return new Result.Success<>(fakeUser);
            }
            throw new Exception("User / Password");
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    /**
     * Enroll new user
     * @param name
     * @param username
     * @param email
     * @return
     */
    public Result<RegisteredUser> enroll(String name, String username, String email) {
        try {
            User user = EnrollmentService.getInstance().enrollUser(name, username, email);
            if(user != null) {
                RegisteredUser fakeUser =
                        new RegisteredUser(user.getName(), user.getLastName(), user.getUserName(), user.getEMail(), user.getToken());
                AccessService.getInstance().closeServiceProvider();
                return new Result.Success<>(fakeUser);
            }
            throw new Exception("User / EMail");
        } catch (Exception e) {
            return new Result.Error(new IOException("Error enrollment", e));
        }
    }

    /**
     * Reset password
     * @param userName
     * @param email
     */
    public void resetPasswod(String userName, String email) {
        //  TODO: Implement it
    }

    /**
     * Logout session
     * @param token
     */
    public void logout(String token) {
        //  TODO: Implement it
    }
}
