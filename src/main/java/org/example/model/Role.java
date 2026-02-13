package org.example.model;

public enum Role {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private final String key;

    // 생성자
    Role(String key) {
        this.key = key;
    }

    // 시큐리티가 권한을 확인할 때 쓸 키값 (위에서 쓴 getKey()가 이거야!)
    public String getKey() {
        return key;
    }
}
