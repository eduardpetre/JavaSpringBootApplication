create table categories
(
    category_id int auto_increment
        primary key,
    tag         varchar(255) not null,
    constraint UKf1ehfphjvwf4hch5era9wxhwx
        unique (tag)
);

create table teams
(
    team_id        int auto_increment
        primary key,
    name           varchar(255) not null,
    no_tournaments int          not null,
    year_founded   varchar(255) not null,
    constraint UKa510no6sjwqcx153yd5sm4jrr
        unique (name)
);

create table players
(
    player_id  int auto_increment
        primary key,
    date_birth varchar(255) not null,
    first_name varchar(255) not null,
    last_name  varchar(255) not null,
    nickname   varchar(255) not null,
    team_id    int          null,
    constraint UK6fw03el6uda0dcep04s4rwpcx
        unique (nickname),
    constraint FK5nglidr00c4dyybl171v6kask
        foreign key (team_id) references teams (team_id)
);

create table tournament_details
(
    tournament_details_id int auto_increment
        primary key,
    attendance            int    not null,
    prize_pool            double not null,
    check (`attendance` >= 0),
    check (`prize_pool` >= 0)
);

create table tournaments
(
    tournament_id         int auto_increment
        primary key,
    title                 varchar(255) not null,
    year                  varchar(255) not null,
    team_id               int          null,
    tournament_details_id int          null,
    constraint UK7p01m4s5dhia1nhuscy3rffu
        unique (tournament_details_id),
    constraint FK9qa3vrifhhxa7sx8ks080mxg
        foreign key (team_id) references teams (team_id),
    constraint FKb2l0vksmad2en3np6dpsl370x
        foreign key (tournament_details_id) references tournament_details (tournament_details_id)
);

create table matches
(
    match_id      int auto_increment
        primary key,
    score         varchar(255) null,
    sport_type    tinyint      null,
    title         varchar(255) not null,
    tournament_id int          null,
    constraint FKeeniokyjgo5k6rmhjujatn27i
        foreign key (tournament_id) references tournaments (tournament_id),
    check (`sport_type` between 0 and 3)
);

create table matches_categories
(
    match_id    int not null,
    category_id int not null,
    constraint FK4faa1eps8f4dan7jrh0jw271f
        foreign key (match_id) references matches (match_id),
    constraint FKqlw9ktoqvm37wj41lu8hl5k34
        foreign key (category_id) references categories (category_id)
);


