define(function () {
    return {
        view: "layout",
        rows: [
            {
                view: "button",
                value: "Home",
                width: 150,
                click: function () {
                    routie('')
                },
            },
            {
                view: 'list',
                height:400,
                width:700,
                data: [
                    'AAAAAAA',
                    'BBBBBBB',
                    'CCCCCCC'
                ]
            }
        ]
    }
})