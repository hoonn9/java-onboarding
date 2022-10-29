package onboarding;

import java.util.*;

public class Problem6 {

    public static List<String> solution(List<List<String>> forms) {
        final DupCheckMap dupCheckMap = new DupCheckMap();
        final HashSet<String> emailSet = new HashSet<>();

        for (List<String> f: forms) {
            Form form = new Form(f.get(0), f.get(1));

            if (form.isPass()) {
                continue;
            }

            for (String part: sliceByLength(form.getNickname(), 2)) {
                if (dupCheckMap.isDuplicate(part, form.getEmail())) {
                    emailSet.add(dupCheckMap.get(part));
                    emailSet.add(form.getEmail());
                }
                dupCheckMap.put(part, form.getEmail());
            }
        }

        ArrayList<String> answer = new ArrayList<>(emailSet);
        answer.sort(Comparator.naturalOrder());

        return answer;
    }

    private static class DupCheckMap extends HashMap<String, String> {
        private boolean isDuplicate(String part, String email) {
            return this.containsKey(part) && !this.get(part).equals(email);
        }
    }

    private static class Form {
        private final String email;
        private final String nickname;

        public boolean isPass() {
            return this.nickname.length() == 1;
        }

        public Form(String email, String nickname) {
            this.email = email;
            this.nickname = nickname;
        }

        public String getEmail() {
            return email;
        }
        public String getNickname() {
            return nickname;
        }
    }

    private static List<String> sliceByLength(String str, Integer len) {
        List<String> result = new ArrayList<>();

        for (int i = 0; i <= str.length() - len; i++) {
            result.add(str.substring(i, i + len));
        }

        return result;
    }
}

