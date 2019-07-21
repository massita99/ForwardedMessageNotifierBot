create table message (
  notify_to_message_id varchar(500) not null,
  chat_id              varchar(500),
  notify_time          timestamp,
  primary key (notify_to_message_id)
);
