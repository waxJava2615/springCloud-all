'use strict'
const merge = require('webpack-merge');
const prodEnv = require('./prod.env');

module.exports = merge(prodEnv, {
  NODE_ENV: '"development"',
  BASE_API_A: '"//admin.starrySky.com:30080"',
  BASE_API_B: '"//admin.starrySky.com:30080"'
});
