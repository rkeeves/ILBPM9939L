# ILBPM9939L

Software testing homework using [Swag Labs App](https://www.saucedemo.com/).

## How to run

Run all tests with surefire: `mvn clean test`

## Notes

### Cookies, local storage

Just for fun, the tests rely extensively on cookies and local storage.

Using cookies and local storage enables us to do less ui interactions, which means:
- shorter execution time (e.g. less clicking, waiting for the pages to load, dom to settle, things to go wrong etc.)
- less brittle/interdependent tests (e.g. changes in the login page only break the account tests, other tests would still run fine.)

### Hardcoded data

I wanted to keep the data within the Gherkin files to fiddle around with datatables.

But, yes, you could definitely configure everything via config files.
(In fact, sometimes it is necessary. For example, imagine if there's some immutable - but still important - data.
And now imagine if that data was different on your locally deployed server, your company's test server,
and the on the 'demo' server. In that case, using configs could be a good idea.
On the other hand - in my experience - the more abstract your gherkin file is - e.g. you just write codes/keys 
into the gherkin file and you look it up from config - the less understandable it becomes.)

The pom is already setup. You could even use snakeyml out-of-the-box because I made sure that the pom is setup in
such a way that springboot's config could be used if the need arises.

(You could do leaner contexts, but for me the context startup time was fine.)
