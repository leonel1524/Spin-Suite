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
package org.erpya.security.ui.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import android.os.AsyncTask;
import android.util.Patterns;

import org.erpya.security.data.LoginRepository;
import org.erpya.security.data.Result;
import org.erpya.security.R;
import org.erpya.security.data.model.SessionInfo;

import java.lang.ref.WeakReference;

public class LoginViewModel extends ViewModel {

    private MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();
    private MutableLiveData<LoginResult> loginResult = new MutableLiveData<>();
    private LoginRepository loginRepository;

    LoginViewModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    LiveData<LoginFormState> getLoginFormState() {
        return loginFormState;
    }

    LiveData<LoginResult> getLoginResult() {
        return loginResult;
    }

    public void login(String username, String password) {
        // can be launched in a separate asynchronous job
        new AccessProcessTask(this).execute(username, password);
    }

    /**
     * Login calling
     * @param username
     * @param password
     */
    private Result<SessionInfo> loginModel(String username, String password) {
        return loginRepository.login(username, password);
    }

    private void setResult(Result<SessionInfo> result) {
        if (result instanceof Result.Success) {
            SessionInfo data = ((Result.Success<SessionInfo>) result).getData();
            loginResult.setValue(new LoginResult(new LoggedInUserView(data.getUserInfo().getDisplayName())));
        } else {
            loginResult.setValue(new LoginResult(R.string.login_failed));
        }
    }

    public void loginDataChanged(String username, String password) {
        if (!isUserNameValid(username)) {
            loginFormState.setValue(new LoginFormState(R.string.invalid_username, null));
        } else if (!isPasswordValid(password)) {
            loginFormState.setValue(new LoginFormState(null, R.string.invalid_password));
        } else {
            loginFormState.setValue(new LoginFormState(true));
        }
    }

    // A placeholder username validation check
    private boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        }
        if (username.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(username).matches();
        } else {
            return !username.trim().isEmpty();
        }
    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 4;
    }

    private static class AccessProcessTask extends AsyncTask<String, Void, Result<SessionInfo>> {
        private final WeakReference<LoginViewModel> loginModel;

        private AccessProcessTask(LoginViewModel loginModel) {
            this.loginModel = new WeakReference<LoginViewModel>(loginModel);
        }

        @Override
        protected Result<SessionInfo> doInBackground(String... params) {
            String username = params[0];
            String password = params[1];
            return loginModel.get().loginModel(username, password);
        }

        @Override
        protected void onPostExecute(Result<SessionInfo> result) {
            LoginViewModel source = loginModel.get();
            if (source != null) {
                source.setResult(result);
            }
        }
    }
}
