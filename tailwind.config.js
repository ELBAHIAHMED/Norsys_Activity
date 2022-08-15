
module.exports = {
  content: ['./src/**/*.{html,ts}', './projects/**/*.{html,ts}'],
  theme: {
    extend: {},
  },
  plugins: [
    require("tailwindcss/plugin"),
    require("tailwindcss/colors")
  ],
 };