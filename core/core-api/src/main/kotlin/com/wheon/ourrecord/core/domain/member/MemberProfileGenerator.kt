package com.wheon.ourrecord.core.domain.member

class MemberProfileGenerator {
    companion object {
        private val ADJECTIVES = listOf(
            "귀여운", "사랑스러운", "용감한", "씩씩한", "엉뚱한",
            "반짝이는", "포근한", "재빠른", "느긋한", "새침한",
            "명랑한", "따뜻한", "장난꾸러기", "수줍은", "도도한",
            "몽실몽실", "통통한", "행복한", "나른한", "폭신한",
            "졸린", "배고픈", "상냥한", "꼬물꼬물", "말랑한",
        )

        private val NOUNS = listOf(
            "바람돌이", "곰돌이", "토끼", "다람쥐", "너구리",
            "부엉이", "펭귄", "고양이", "강아지", "여우",
            "판다", "수달", "오리", "햄스터", "고슴도치",
            "코알라", "알파카", "병아리", "문어", "개구리",
            "물개", "두더지", "하마", "고래", "참새",
        )

        private val EMOJIS = listOf(
            "🐰", "🐻", "🐼", "🦊", "🐱", "🐶", "🐧", "🦉",
            "🐿️", "🦦", "🐨", "🦙", "🐥", "🐸", "🐙", "🐹",
            "🦔", "🦭", "🐳", "🐤", "🌸", "✨", "🍀", "🌷",
        )

        fun generate(): MemberProfile {
            return MemberProfile(
                nickname = ADJECTIVES.random() + NOUNS.random(),
                emoji = EMOJIS.random(),
            )
        }
    }
}
