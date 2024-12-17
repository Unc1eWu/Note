const app = Vue.createApp({
    data() {
        return {
            showBooks: true,
            // title: 'The Final Empire',
            // author : 'Brandon Sanderson',
            books: [
                { title: 'Name of the Wind', author: 'Patrick Rothfuss', isFav: true},
                { title: 'The Way of Kings', author: 'Brandon Sanderson', isFav: false},
                { title: 'The Final Empire', author: 'Brandon Sanderson', isFav: true},
            ],
            age: 45,
            x: 0,
            y: 0,
        }
    },
    methods: {
        changeTitle(title) {
            console.log('you click me')
            // this.title = 'Words of Radiance'
            this.title = title
        },

        toggleShowBooks() {
            this.showBooks = !this.showBooks
        },

        handleEvent(e, data) {
           console.log(e, e.type)
           if (data) {
               console.log(data)
           }
        },

        handleMousemove(e) {
            this.x = e.offsetX
            this.y = e.offsetY
        },

        toogleFav(book) {
            book.isFav = !book.isFav
        },
    },
    
    computed: {
        filteredBooks() {
            return this.books.filter((book) => book.isFav)
        }
    }
})

app.mount('#app')