CREATE TABLE gdax.test_table (
	bucket int,
	sequence int,
	maker_order_id text,
	price text,
	product_id text,
	side text,
	size text,
	taker_order_id text,
	time text,
	trade_id text,
	type text,
	PRIMARY KEY (bucket, sequence)
) WITH bloom_filter_fp_chance = 0.01
AND comment = ''
AND crc_check_chance = 1.0
AND dclocal_read_repair_chance = 0.1
AND default_time_to_live = 0
AND gc_grace_seconds = 864000
AND max_index_interval = 2048
AND memtable_flush_period_in_ms = 0
AND min_index_interval = 128
AND read_repair_chance = 0.0
AND speculative_retry = '99.0PERCENTILE'
AND caching = {
	'keys' : 'ALL',
	'rows_per_partition' : 'NONE'
}
AND compression = {
	'chunk_length_in_kb' : 64,
	'class' : 'LZ4Compressor',
	'enabled' : true
}
AND compaction = {
	'bucket_high' : 1.5,
	'bucket_low' : 0.5,
	'class' : 'SizeTieredCompactionStrategy',
	'enabled' : true,
	'max_threshold' : 32,
	'min_sstable_size' : 50,
	'min_threshold' : 4,
	'tombstone_compaction_interval' : 86400,
	'tombstone_threshold' : 0.2,
	'unchecked_tombstone_compaction' : false
};

INSERT INTO gdax.test_table (bucket, sequence, type, trade_id, time, product_id, maker_order_id, taker_order_id, size, price, side)
    VALUES (861006441, "match", 8373507, "2017-07-25T23:15:31.621000Z", "ETH-USD", "8caa7e8a-a3f5-46fe-812d-099778489037", "0e2b6bcd-352d-4f44-9ca3-12e02be0c089", "0.12687980", "200.50000000", "sell");

INSERT INTO gdax.test_table (bucket, sequence, type, trade_id, time, product_id, maker_order_id, taker_order_id, size, price, side)
    VALUES (861006442, "match", 8373508, "2017-07-25T23:15:33.621000Z", "ETH-USD", "8caa7e8a-a3f5-46fe-812d-099778489037", "0e2b6bcd-352d-4f44-9ca3-12e02be0c089", "0.12687980", "200.50000000", "buy");

INSERT INTO gdax.test_table (bucket, sequence, type, trade_id, time, product_id, maker_order_id, taker_order_id, size, price, side)
    VALUES (861006443, "match", 8373509, "2017-07-25T23:15:35.621000Z", "ETH-USD", "8caa7e8a-a3f5-46fe-812d-099778489037", "0e2b6bcd-352d-4f44-9ca3-12e02be0c089", "0.12687980", "200.50000000", "sell");

INSERT INTO gdax.test_table (bucket, sequence, type, trade_id, time, product_id, maker_order_id, taker_order_id, size, price, side)
    VALUES (861006444, "match", 8373510, "2017-07-25T23:15:36.621000Z", "ETH-USD", "8caa7e8a-a3f5-46fe-812d-099778489037", "0e2b6bcd-352d-4f44-9ca3-12e02be0c089", "0.12687980", "200.50000000", "sell");

INSERT INTO gdax.test_table (bucket, sequence, type, trade_id, time, product_id, maker_order_id, taker_order_id, size, price, side)
    VALUES (861006445, "match", 8373511, "2017-07-25T23:15:38.621000Z", "ETH-USD", "8caa7e8a-a3f5-46fe-812d-099778489037", "0e2b6bcd-352d-4f44-9ca3-12e02be0c089", "0.12687980", "200.50000000", "buy");