Service provides a random GIF based on difference between current rate and yesterday rate of currency.

If current rate > previous rate, service provides gif founded by search tag "rich".

If current rate < previous rate, tag "broke".

If current rate = previous rate, tag "keep calm".

After starting the app, browse the address:

http://hostname:port/rates/{baseCurrencyCode}, (example: http://localhost:8081/rates/usd)

change the variable "baseCurrencyCode" by existed currency code (note: service provide only "USD", because of free API using), port by default is 8081.

The currency, the rate bases on, is specified in "application.properties" with key "demo.exchangeCurrencyCode".
