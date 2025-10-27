package com.latinhouse.backend.port.out.user;

import com.latinhouse.backend.domain.user.User;

public interface UpdateUserPort {
    User update(User toBe);
}
